import React from 'react'

import Card from '../componentes/card'
import FormGroup from '../componentes/form-group'
import axios from 'axios'
import CadastroContato from './cadastroContato'

class Cadastro extends React.Component{
    
    render(){
        return(
             <Card title="Cadastro de Pessoa">
                <div className="row">                
                   <div className="col-lg-12">
                        <div className="bs-component">
                            <FormGroup label="Nome *" htmlFor="inputNome">
                                <input type="text" 
                                id="inputNome" 
                                className="form-control"
                                name="nome" 
                                onChange={e => this.setState({nome: e.target.value})} />
                            </FormGroup>

                            <FormGroup label="CPF: *" htmlFor="inputCpf">
                                <input type="cpf"
                                id="inputCpf"
                                className="form-control"
                                name="cpf"
                                onChange={e => this.setState({cpf: e.target.value})} />
                            </FormGroup>

                            <FormGroup label="Data de Nascimento: *" htmlFor="inputDtNascimento">
                                <input type="dtNascimento"
                                id="inputDtNascimento"
                                className="form-control"
                                name="dtNascimento"
                                onChange={e => this.setState({dtNascimento: e.target.value})} />
                            </FormGroup>

                            <button onClick={this.enviarParaCadastroContato} type = "button" className="btn btn-success">Salvar</button>
                            <button type = "button" className="btn btn-danger">Cancelar</button>
                        </div>
                    </div>
                </div>
            </Card>
        )
    }
}

export default Cadastro