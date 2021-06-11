import React from  'react';
import './App.css';
import ProbaForm from './ProbaForm'
import ProbaTable from './Proba'
import {GetProbe, DeleteProba, AddProba, GetProbaId, GetProbaDescriere, UpdateProba} from './utils/rest-calls'

class App extends React.Component{
  constructor(props){
    super(props)
    this.state={probe: [],
    getId: this.getId.bind(this),
    getDesc: this.getDesc.bind(this),
    deleteFunc: this.deleteFunc.bind(this),
    addFunc: this.addFunc.bind(this),
    findAllFunc: this.findAllFunc.bind(this),
    updateFunc: this.updateFunc.bind(this)
    }
    console.log('App constructor')
  }

  findAllFunc(){
    GetProbe().then(probe => this.setState({probe}))
  }

  getId(id){
    console.log('inside get_id func ' + id)
    GetProbaId(id).then(probe => this.setState({probe}))
  }

  getDesc(desc){
    console.log('inside get_desc func ' + desc)
    GetProbaDescriere(desc).then(probe => this.setState({probe}))
  }

  addFunc(proba){
    console.log('inside add Func' + proba)
    AddProba(proba)
      .then(res => GetProbe())
      .then(probe => this.setState({probe}))
      .catch(error => console.log('eroare add ', error))
  }

  updateFunc(id, proba){
    console.log('inside update func ' + id + ' ' + proba)
    UpdateProba(id, proba)
      .then(res => GetProbe())
      .then(probe => this.setState({probe}))
      .catch(error => console.log('error ', error))
  }

  deleteFunc(id){
    console.log('inside deleteFunc ' + id)
    DeleteProba(id)
      .then(res => GetProbe())
      .then(probe => this.setState({probe}))
      .catch(error => console.log('eroare delete ', error))
  }

  componentDidMount(){
    console.log('inside componentDidMount')
    GetProbe().then(probe => this.setState({probe}))
  }

  render(){
    return(
      <div className="ReactClient">
        <h1>Probe</h1>
        <ProbaForm addFunc={this.state.addFunc} getId={this.state.getId} findAll={this.state.findAllFunc} getDesc={this.state.getDesc} updateFunc={this.state.updateFunc}/>
        <br/>
        <br/>
        <ProbaTable probe={this.state.probe} delFunc={this.state.deleteFunc}/>
      </div>
    )
  }
}

export default App;
