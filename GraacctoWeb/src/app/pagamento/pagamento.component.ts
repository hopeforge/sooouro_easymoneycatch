import { Component, OnInit } from '@angular/core';
import { PagamentoService } from '../Service/pagamento.service';
import { Pagamento } from '../models/pagamento.model';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css']
})
export class PagamentoComponent implements OnInit {

  hide = true;
  id: number;
  pagamento = new Pagamento();

  constructor(private pagamentoService: PagamentoService,
    public snackBar: MatSnackBar) { }

  ngOnInit() {

    this.id = parseInt( localStorage.getItem('usuarioSessao'));
    this.gerarPagamento();
  }

  gerarPagamento(){
    this.pagamentoService.gerarPagamento(this.id).subscribe((resposta: any) => {
      this.pagamento = resposta;
    }, erro => {
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
