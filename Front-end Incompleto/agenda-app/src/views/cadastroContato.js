import React from 'react'

import Card from '../componentes/card'
import FormGroup from '../componentes/form-group'
import axios from 'axios'

class CadastroContato extends React.Component{

    state = {
        nome: '',
        telefone: '',
        email: ''
    }

    cadastrarBanco =(props) =>{
        
        axios.post('http://localhost:8180/pessoa', {
            
            nome: this.state.nome,
            telefone: this.state.telefone,
            email: this.state.email
        }).then (Response => {
            console.log(Response)
        }).catch( erro => {
            console.log(erro.Response)
        })
    }

    voltarHome = () =>{
        
    }

    render(){

        return(

            <Card title="Cadastro de Contatos">
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

                            <FormGroup label="Telefone: *" htmlFor="inputTelefone">
                                <input type="telefone"
                                id="inputTelefone"
                                className="form-control"
                                name="telefone"
                                onChange={e => this.setState({telefone: e.target.value})} />
                            </FormGroup>

                            <FormGroup label="Email: *" htmlFor="inputEmail">
                                <input type="email"
                                id="inputEmail"
                                className="form-control"
                                name="email"
                                onChange={e => this.setState({email: e.target.value})} />
                            </FormGroup>

                            <button onClick={this.cadastrarBanco} type = "button" className="btn btn-success">Salvar</button>
                            <button onClick={this.voltarHome} type = "button" className="btn btn-danger">Home</button>
                        </div>
                    </div>
                </div>
            </Card>
        )
    }
}

export default CadastroContato