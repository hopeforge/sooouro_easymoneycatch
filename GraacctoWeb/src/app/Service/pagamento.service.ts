import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pagamento } from '../models/pagamento.model';
import { GRAACC_API } from './graaccto.api';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  pagamento = new Pagamento();
  constructor(private http: HttpClient) { }

  gerarPagamento(id: number) {
    return this.http.get<[Pagamento]>(`${GRAACC_API}/doacao/gerarPagamento?id=${id}`);
  }
}
