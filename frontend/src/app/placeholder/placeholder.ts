import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-placeholder',
  templateUrl: './placeholder.html',
  styleUrl: './placeholder.css',
})
export class Placeholder {
  protected readonly title: string;

  constructor(route: ActivatedRoute) {
    this.title = (route.snapshot.data as { title: string }).title ?? 'Coming Soon';
  }
}
