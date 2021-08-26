import React from 'react'

import { Route, Switch, HashRouter } from 'react-router-dom'
import Cadastro from '../views/cadastro'
import Home from '../views/home'

function Rotas(){
    return(
        <HashRouter>
            <Switch>
                <Route path="/home" component={Home} />
                <Route path="/cadastroPessoa" component={Cadastro} />
            </Switch>
        </HashRouter>
    )
}

export default Rotas