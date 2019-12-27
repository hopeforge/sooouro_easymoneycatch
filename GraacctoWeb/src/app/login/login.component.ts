import { Component, OnInit } from '@angular/core';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material';
import { Usuario } from '../models/usuario.model';
import { LoginService } from '../Service/login.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  status: boolean;
  usuario = new Usuario();
  hide = true;

  constructor(public snackBar: MatSnackBar,
    private route: Router,
    private loginService: LoginService) { }

  ngOnInit() {

  }

  logar(){
    this.loginService.logar(this.usuario.email, this.usuario.senha).subscribe((resultado: any) => {

      if (resultado.payload !== null) {
        console.log(resultado);
        localStorage.setItem('usuarioSessao', resultado.payload.id);
        console.log(localStorage);
        this.route.navigate(['/dashboard']);
      }else{
        this.openSnackBar('Login e/ou senha incorretos!', 'Ok', this.status);
      }
    },
     erro => {
      this.openSnackBar('Login e/ou senha incorretos!', 'Ok', this.status);
     }
    );
  }


  openSnackBar(message: string, action: string, status: boolean) {
    const config = new MatSnackBarConfig();
    config.duration = 7000;
    if (this.status === true) {
      config.panelClass = ['ok-Snackbar'];
    } else {
      config.panelClass = ['errSnackbar'];
    }
    this.snackBar.open(message, action, config);
  }

}
