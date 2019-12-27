import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuracao } from '../models/configuracao.model';
import { GRAACC_API } from './graaccto.api';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracaoService {

  constructor(private http: HttpClient) { }

  salvar(configuracao: Configuracao) {

    return this.http.post<Configuracao>(`${GRAACC_API}/configuracao/salvar`, configuracao);
 }

 buscaPorId(id: number) {
   return this.http.get(`${GRAACC_API}/configuracao/consultar?id=${id}`);
 }
}
