import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})
export class App implements OnInit {
  protected readonly navItems = [
    { label: 'Dashboard', path: '/dashboard' },
    { label: 'Forecasts', path: '/forecasting' },
    { label: 'Comparisons', path: '/comparisons' },
    { label: 'Monitoring', path: '/monitoring' },
    { label: 'Simulation', path: '/simulation' },
  ];

  // Expose user observable for template
  get user$() {
    return this.auth.user$;
  }

  constructor(
    private auth: AuthService,
    private router: Router,
  ) {}

  // Controls visibility of the inline logout confirmation modal
  showLogoutConfirm = false;

  async ngOnInit(): Promise<void> {
    try {
      // Check if there might be a session cookie. If so, try to fetch the user info.
      if (this.auth.mayHaveSession()) {
        await this.auth.me();
      }
    } catch (ignored) {}
  }

  /**
   * Opens the logout confirmation dialog.
   */
  openLogoutConfirm(): void {
    this.showLogoutConfirm = true;
  }

  /**
   * Closes the logout confirmation dialog.
   */
  cancelLogout(): void {
    this.showLogoutConfirm = false;
  }

  /**
   * Performs the logout and shows a toast for user feedback.
   */
  async confirmLogout(): Promise<void> {
    this.showLogoutConfirm = false;
    const Swal = (await import('sweetalert2')).default;
    try {
      await this.auth.logout();
      await this.router.navigate(['/login']);
      await Swal.fire({
        icon: 'success',
        title: 'Logged out',
        timer: 1000,
        showConfirmButton: false,
        background: '#2e3338',
        color: '#ffffff',
        iconColor: '#ef4444',
      });
    } catch (e) {
      console.error(
        'Logout failed while calling auth.logout()',
        e instanceof Error ? e.message : e,
      );
      await Swal.fire({
        icon: 'error',
        title: 'Logout failed',
        text: 'Please try again or refresh the page.',
        background: '#2e3338',
        color: '#ffffff',
        iconColor: '#ef4444',
      });
    }
  }
}
