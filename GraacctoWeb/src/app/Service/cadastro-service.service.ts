import { Injectable } from '@angular/core';
import { Usuario } from '../models/usuario.model';
import { GRAACC_API } from './graaccto.api';
import { HttpClient } from '@angular/common/http';
import { Configuracao } from '../models/configuracao.model';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(private http: HttpClient) { }

  salvar(usuario: Usuario) {

     return this.http.post<Usuario>(`${GRAACC_API}/usuario/salvar`, usuario);
  }

  buscaPorId(id: number) {
    return this.http.get(`${GRAACC_API}/usuario/consultar?id=${id}`);
  }
}
