import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['../app.css', './login.css'],
})
export class Login {
  username = '';
  password = '';
  error: string | null = null;

  constructor(private router: Router, private auth: AuthService, private cd: ChangeDetectorRef) {}

  async onSubmit(e: Event) {
    e.preventDefault();
    this.error = null;
    try {
      await this.auth.login(this.username, this.password);

      // Login successful => navigate to dashboard
      await this.router.navigate(['/dashboard']);
    } catch (res) {
      // Login not successful:
      try {
        // If backend returned an error message, parse and show it
        const err = await (res as Response).json();
        this.error = err?.message || 'Login failed';
      } catch (e) {
        // If backend did not return error message, use response status (if available) or generic message
        if (res && (res as Response).status) {
          const r = res as Response;
          this.error = r.statusText || `Login failed (status ${r.status})`;
        } else {
          this.error = 'Login failed';
        }
      }
      // Ensure change-Detection so the template updates.
      try {
        this.cd.detectChanges();
      } catch (ignored) {}
    }
  }
}





