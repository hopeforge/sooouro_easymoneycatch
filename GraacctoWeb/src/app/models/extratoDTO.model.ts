import { Validator } from '@angular/forms';

export class ExtratoDTO {
  id: number;
  nome: string;
  quantidade: string;
  valor: string;

  constructor(){
    this.id = null;
    this.nome = null;
    this.quantidade = null;
    this.valor = null;
  }
}
