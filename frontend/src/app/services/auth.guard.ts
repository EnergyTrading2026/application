import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

/**
 * AuthGuard to restrict page access in the frontend.
 * @param route Route that is being accessed.
 * @param state State of the router at the time of access.
 * @returns A boolean indicating whether the route can be activated.
 */
export const authGuard: CanActivateFn = async (route, state) => {
  const router = inject(Router);
  const auth = inject(AuthService);
  // If currentUser exists, allow routing
  if (auth.currentUser) {
    return true;
  }

  try {
    // Check if there might be a session cookie. If so, try to fetch the user info.
    if (auth.mayHaveSession()) {
      await auth.me();
      return true;
    }
    // No session cookie -> redirect to login
    await router.navigate(['/login']);
    return false;
  } catch (e) {
    // not authenticated -> redirect to login
    await router.navigate(['/login']);
    return false;
  }
};



