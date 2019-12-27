import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GRAACC_API } from './graaccto.api';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  logar(login: String, senha: String){
    return this.http.get(`${GRAACC_API}/usuario/logar?login=${login}&senha=${senha}`);
  }
}
