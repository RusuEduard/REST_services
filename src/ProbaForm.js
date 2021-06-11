import React from  'react';

class ProbaForm extends React.Component{
  constructor(props){
    super(props)
    this.state = {id: '', descriere: '', varstaMinima: '', varstaMaxima: ''}
  }

  handleDescChange=(event) =>{
    this.setState({descriere: event.target.value})
  }

  handleMinChange=(event) =>{
    this.setState({varstaMinima: event.target.value})
  }

  handleMaxChange=(event) =>{
    this.setState({varstaMaxima: event.target.value})
  }

  handleIdChange=(event) =>{
    this.setState({id: event.target.value})
  }

  handleSubmit = (event) =>{
    var proba = {
      descriere: this.state.descriere,
      varstaMinima: this.state.varstaMinima,
      varstaMaxima: this.state.varstaMaxima
    }
    console.log('Proba a fost trimisa')
    console.log(proba)
    this.props.addFunc(proba)
    event.preventDefault()
    this.setState({
      id: '',
      descriere: '',
      varstaMinima: '',
      varstaMaxima: ''
    })
  }

  update = (event) =>{
    var proba={
      descriere: this.state.descriere
    }
    var id = this.state.id
    console.log('Modifica proba cu id ' + id)
    console.log(proba)
    this.props.updateFunc(id, proba)
    event.preventDefault()
    this.setState({
      id: '',
      descriere: '',
      varstaMinima: '',
      varstaMaxima: ''
    })
  }

  findId = (event)=>{
    var id = this.state.id
    console.log(id)
    this.props.getId(id)
    event.preventDefault()
    this.setState({
      id: '',
      descriere: '',
      varstaMinima: '',
      varstaMaxima: ''
    })
  }

  findDesc = (event)=>{
    var desc = this.state.descriere
    console.log(desc)
    this.props.getDesc(desc)
    event.preventDefault()
    this.setState({
      id: '',
      descriere: '',
      varstaMinima: '',
      varstaMaxima: ''
    })
  }

  findAll = (event)=>{
    this.props.findAll()
    event.preventDefault()
    this.setState({
      id: '',
      descriere: '',
      varstaMinima: '',
      varstaMaxima: ''
    })
  }

  render(){
    return(
      <form onSubmit={this.handleSubmit}>
        <label>
          ID:
          <input type="text" value={this.state.id} onChange={this.handleIdChange} />
        </label>
        <br/>

        <label> 
          Descriere: 
          <input type="text" value={this.state.descriere} onChange={this.handleDescChange}/>
        </label>
        <br/>

        <label>
          Varsta minima:
          <input type="text" value={this.state.varstaMinima} onChange={this.handleMinChange}/>
        </label>
        <br/>

        <label>
          Varsta maxima:
          <input type="text" value={this.state.varstaMaxima} onChange={this.handleMaxChange}/>
        </label>
        <br/>

        <input type="submit" value="Add"/>
        <button onClick={this.update}>Update</button>
        <button onClick={this.findId}>Find id</button>
        <button onClick={this.findDesc}>Find desc</button>
        <button onClick={this.findAll}>Find all</button>
      </form>
    )
  }
}

export default ProbaForm;