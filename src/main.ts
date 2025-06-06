import 'zone.js';
import { enableProdMode, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { environment } from './app/environments/environment';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideNativeDateAdapter } from '@angular/material/core'; // <-- IMPORTA ESTO

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(App, {
  providers: [
    importProvidersFrom(BrowserAnimationsModule, HttpClientModule),
    provideAnimations(),
    provideNativeDateAdapter() 
  ]
}).catch(err => console.error(err));