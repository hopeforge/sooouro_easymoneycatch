import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GRAACC_API } from './graaccto.api';
import { Grato } from '../models/grato.model';

@Injectable({
  providedIn: 'root'
})
export class GratoService {

  constructor(private http: HttpClient) { }

  listar(id: number){
    return this.http.get<Grato[]>(`${GRAACC_API}/grato/listar?id=${id}`);
  }

  salvar(id: number, valor: number){
    return this.http.get(`${GRAACC_API}/grato/atualizarValor?id=${id}&valor=${valor}`);
  }
}
