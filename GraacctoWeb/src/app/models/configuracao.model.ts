import { Timestamp } from 'rxjs';
import { Usuario } from './usuario.model';

export class Configuracao{

  id: number;
  empresa: Usuario;
  quantidade: number;
  tempo: Date;
  fidelidade: number;

  constructor(){
    this.id = null;
    this.empresa = null;
    this.quantidade = null;
    this.tempo = null;
    this.fidelidade = null;
  }

}
