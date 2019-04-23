import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { ProfileComponent } from './profile';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'profile/:username', component: ProfileComponent },
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

  export class AppRoutingModule { }
 export const routing = RouterModule.forRoot(appRoutes);