import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `<app-login></app-login>
             <app-register></app-register>`,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
}
