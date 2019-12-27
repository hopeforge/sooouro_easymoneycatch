import { Component, OnInit } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material/icon';
import { Grato } from '../models/grato.model';
import { GratoService } from '../Service/grato.service';
import { Router } from '@angular/router';
import { MatSnackBarConfig, MatSnackBar, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  displayedColumns: string[] = ['id', 'parceiro', 'grato', 'valor', 'botoes'];
  dataSource = new MatTableDataSource<Grato>();
  grato = new Grato();
  listaGrato: Grato[];
  openDialog: any;
  id: number;

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer,
    private gratoService: GratoService,
    private router: Router,
    public snackBar: MatSnackBar) {
    iconRegistry.addSvgIcon(
        'thumbs-up',
        sanitizer.bypassSecurityTrustResourceUrl('assets/img/examples/thumbup-icon.svg'));
  }

  ngOnInit() {
    this.id = parseInt( localStorage.getItem('usuarioSessao'));
    this.listar(this.id);
  }

  salvar(id: number, valor: number){
    console.log(id, valor);
    this.gratoService.salvar(id, valor).subscribe(
      resultado => {
       // this.status = true;
        this.openSnackBar('Valor salvo com sucesso!', 'Ok', true);
        this.listar(this.id);
      },
       erro => {
        //this.status = false;
        this.openSnackBar('Erro ao salvar valor!', 'Ok', false);
       }
    );
  }

  listar(id: number) {
    this.gratoService.listar(id).subscribe(
      (resultado: any) => {
        console.log(resultado);
        this.listaGrato = resultado;
        console.log(this.listaGrato);
       // this.status = true;
        //this.openSnackBar('Cadastro salvo com sucesso!', 'Ok', this.status);
      },
       erro => {
        // this.status = false;
        this.openSnackBar('Erro ao salvar cadastro!', 'Ok', false);
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
