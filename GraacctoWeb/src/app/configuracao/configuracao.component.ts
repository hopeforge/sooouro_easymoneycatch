import { Component, OnInit } from '@angular/core';
import { ConfiguracaoService } from '../Service/configuracao.service';
import { Configuracao } from '../models/configuracao.model';
import { MatSnackBarConfig, MatSnackBar } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../models/usuario.model';

@Component({
  selector: 'app-configuracao',
  templateUrl: './configuracao.component.html',
  styleUrls: ['./configuracao.component.css']
})
export class ConfiguracaoComponent implements OnInit {

  checked = false;
  fidelidade: boolean;
  configuracao= new Configuracao();

  constructor(private configuracaoService: ConfiguracaoService,
    private route: ActivatedRoute,
    private router: Router,
    public snackBar: MatSnackBar
    ) { }

  ngOnInit() {
    const id = parseInt( localStorage.getItem('usuarioSessao'));
    this.buscaPorId(id);
  }

    salvar(){
      let usuario = new Usuario();
      usuario.id = parseInt( localStorage.getItem('usuarioSessao'));
      if(this.fidelidade){
          this.configuracao.fidelidade = 1;
      } else {
        this.configuracao.fidelidade = 0;
      }
      this.configuracao.empresa = usuario;
      console.log(this.configuracao);
    this.configuracaoService.salvar(this.configuracao).subscribe(resultado => {
      this.router.navigate(['/dashboard']);
      this.openSnackBar('Configuração salva com sucesso!', 'Ok', true);
    },
     erro => {
      this.openSnackBar('Erro ao salvar configuração!', 'Ok', false);
     })
  }

    buscaPorId(id:number){
      this.configuracaoService.buscaPorId(id).subscribe((resultado: any) => {
        this.configuracao = resultado.payload;
        console.log(this.configuracao);
      },
       erro => {
        this.openSnackBar('Erro ao buscar configuração!', 'Ok', false);
      }
       );
    }
  openSnackBar(message: string, action: string, status: boolean) {
    const config = new MatSnackBarConfig();
    config.duration = 7000;
    if (status === true) {
    config.panelClass = ['ok-Snackbar'];
    } else {
    config.panelClass = ['errSnackbar'];
    }
    this.snackBar.open(message, action, config);
  }


}
