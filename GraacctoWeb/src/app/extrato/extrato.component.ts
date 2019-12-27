import { Component, OnInit } from '@angular/core';
import { ExtratoService } from '../Service/extrato.service';
import { ExtratoDTO } from '../models/extratoDTO.model';
import { MatSnackBarConfig, MatSnackBar, MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { IfStmt } from '@angular/compiler';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {

  listaExtrato: ExtratoDTO[];
  displayedColumns: string[] = ['id', 'nome', 'quantidade', 'valor'];
  dataSource = new MatTableDataSource<ExtratoDTO>();
  id: number;

  constructor(private extratoService: ExtratoService,
    private router: Router,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
      this.id = parseInt( localStorage.getItem('usuarioSessao'));

  }

  selecao(seletor){
      if(seletor === 1) {
        this.listarEmpresaGrato(this.id);
      } else {
        this.listarEmpresaDoacao(this.id);
      }
  }

  listarEmpresaGrato(id: number){
      this.extratoService.listarEmpresaGrato(id).subscribe((response: any) => {
        console.log(response);
        this.listaExtrato = response;
        console.log(this.listaExtrato);
      },
        erro => {
          this.openSnackBar('Erro ao carregar a lista!', 'Ok', false);
        }
      )
  }

  listarEmpresaDoacao(id: number){
    this.extratoService.listarEmpresaDoacao(id).subscribe((response: any) => {
      console.log(response);
      this.listaExtrato = response;
      console.log(this.listaExtrato);
    },
      erro => {
        this.openSnackBar('Erro ao carregar a lista!', 'Ok', false);
      }
    )
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
