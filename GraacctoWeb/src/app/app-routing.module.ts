import { QrcodeComponent } from './qrcode/qrcode.component';
import { ExtratoComponent } from './extrato/extrato.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { ConfiguracaoComponent } from './configuracao/configuracao.component';
import { LoginComponent } from './login/login.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'configuracao', component: ConfiguracaoComponent},
  {path: 'pagamento', component: PagamentoComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'extrato', component: ExtratoComponent},
  {path: 'qrcode', component: QrcodeComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
