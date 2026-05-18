import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

/**
 * Represents an authenticated user with a username and a list of roles.
 */
export interface User {
  username: string;
  roles: string[];
}

/**
 * AuthService to manage login and logout.
 */
@Injectable({ providedIn: 'root' })
export class AuthService {
  private userSubject = new BehaviorSubject<User | null>(null);
  user$ = this.userSubject.asObservable();

  /**
   * Returns the current user.
   */
  get currentUser(): User | null {
    return this.userSubject.getValue();
  }

  /**
   * Get the value for a cookie by name. Returns null if not found.
   * @param name The name of the cookie to retrieve.
   * @returns The cookie value, or null if not found.
   */
  private getCookie(name: string): string | null {
    if (!document.cookie) return null;
    const cookies = document.cookie.split(';').map(c => c.trim());
    for (const c of cookies) {
      if (c.startsWith(name + '=')) {
        return decodeURIComponent(c.substring(name.length + 1));
      }
    }
    return null;
  }

  /**
   * Checks if there are any cookies that indicate the user might be logged in.
   */
  mayHaveSession(): boolean {
    if (!document.cookie) return false;
    const cookies = document.cookie.split(';').map(c => c.trim());
    const candidateNames = ['SESSION', 'JSESSIONID', 'connect.sid', 'session', 'auth', 'token', 'jwt', 'XSRF-TOKEN'];
    for (const c of cookies) {
      const [k] = c.split('=');
      if (!k) continue;
      const key = k.trim();
      for (const cand of candidateNames) {
        if (key.toLowerCase().includes(cand.toLowerCase())) return true;
      }
    }
    return false;
  }

  /**
   * Attempts to log in with the provided username and password.
   * @param username Username
   * @param password Password
   */
  async login(username: string, password: string): Promise<User> {
    const csrf = this.getCookie('XSRF-TOKEN');
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        ...(csrf ? { 'X-XSRF-TOKEN': csrf } : {}),
      },
      body: JSON.stringify({ username, password }),
    });
    if (!res.ok) throw res;
    const data = await res.json();
    const user: User = { username: data.username, roles: data.roles };
    this.userSubject.next(user);
    return user;
  }

  /**
   * Fetches the current user's information from the backend.
   */
  async me(): Promise<User> {
    const res = await fetch('/api/auth/me', { credentials: 'include' });
    if (!res.ok) throw res;
    const data = await res.json();
    const user: User = { username: data.username, roles: data.roles };
    this.userSubject.next(user);
    return user;
  }

  /**
   * Logs the current user out.
   */
  async logout(): Promise<void> {
    const csrf = this.getCookie('XSRF-TOKEN');
    const res = await fetch('/api/auth/logout', {
      method: 'POST',
      credentials: 'include',
      headers: {
        ...(csrf ? { 'X-XSRF-TOKEN': csrf } : {}),
      },
    });

    // clear local user state.
    this.userSubject.next(null);
  }
}
