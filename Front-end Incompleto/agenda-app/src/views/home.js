import React from 'react'

class Home extends React.Component{

    render(){

        return(

            <div className="jumbotron">
                <h1 className="display-3">Olá, seja bem-vindo a minha aplicação!</h1>
                <p className="lead">Esse é meu teste prático.</p>
                <p className="lead">Utilizei para realizar esse projeto as seguintes tecnologias: SpringBoot, React, Bootstrap.</p>
                <hr className="my-4"/>
                <p>Qualquer dúvida estou a disposição para sanar, muito obrigado por essa oportunidade!</p>
                <p className="lead">
                <a className="btn btn-primary btn-lg" href="#/cadastroPessoa" role="button"><i className="fa fa-users"></i>Cadastrar Pessoas</a>
                </p>
            </div>
        )
    }
}

export default Home