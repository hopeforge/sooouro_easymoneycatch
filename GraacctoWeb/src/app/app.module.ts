import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { CadastroComponent } from './cadastro/cadastro.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ConfiguracaoComponent } from './configuracao/configuracao.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { ExtratoComponent } from './extrato/extrato.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FooterComponent } from './footer/footer.component';
import { QrcodeComponent } from './qrcode/qrcode.component';
import { NgxQRCodeModule } from 'ngx-qrcode2';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CadastroComponent,
    ConfiguracaoComponent,
    PagamentoComponent,
    ExtratoComponent,
    DashboardComponent,
    FooterComponent,
    QrcodeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxQRCodeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
