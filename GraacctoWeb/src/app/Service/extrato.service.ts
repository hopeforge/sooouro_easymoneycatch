import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExtratoDTO } from '../models/extratoDTO.model';
import { GRAACC_API } from './graaccto.api';

@Injectable({
  providedIn: 'root'
})
export class ExtratoService {

  extratoDTO = new ExtratoDTO();
  constructor(private http: HttpClient) { }

  listarEmpresaGrato(id: number){
    return this.http.get<ExtratoDTO[]>(`${GRAACC_API}/grato/listarEmpresa?id=${id}`);
  }

  listarEmpresaDoacao(id: number){
    return this.http.get<ExtratoDTO[]>(`${GRAACC_API}/doacao/listarEmpresa?id=${id}`);
  }

}
