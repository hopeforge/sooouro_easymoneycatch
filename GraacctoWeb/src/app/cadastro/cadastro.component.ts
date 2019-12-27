import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../models/usuario.model';
import { CadastroService } from '../Service/cadastro-service.service';
import { NgForm } from '@angular/forms';
import { MatSnackBarConfig, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  form: NgForm;
  status: boolean;
  hide: boolean;
  hide2:boolean;

  usuario = new Usuario();
  constructor(private cadastroService: CadastroService,
    private route: ActivatedRoute,
    private router: Router,
    public snackBar: MatSnackBar){}

  ngOnInit() {
    const id = parseInt( localStorage.getItem('usuarioSessao'));
    this.buscaPorId(id);
  }

  salvar(){

    this.usuario.login = this.usuario.email;
    this.usuario.tipo = '1';
    this.cadastroService.salvar(this.usuario).subscribe(
      resultado => {
        this.status = true;
        this.router.navigate(['/dashboard']);
        this.openSnackBar('Cadastro salvo com sucesso!', 'Ok', this.status);
      },
       erro => {
         this.status = false;
        this.openSnackBar('Erro ao salvar cadastro!', 'Ok', this.status);
       }
    );
  }

  buscaPorId(id: number){
    this.cadastroService.buscaPorId(id).subscribe((resultado: any) => {
      console.log(resultado);
      this.usuario = resultado;
      console.log(this.usuario);
      },
       erro => {
         this.status = false;
        //this.openSnackBar('Erro ao buscar cadastro!', 'Ok', this.status);
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
