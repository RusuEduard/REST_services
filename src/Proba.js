import React from 'react';

class ProbaRow extends React.Component{

  handleClick = (event) => {
    console.log('Delete ' + this.props.proba.id)
    this.props.delFunc(this.props.proba.id)
  }

  render(){
    return(
      <tr>
        <td>
          {this.props.proba.id}
        </td>
        <td>
          {this.props.proba.descriere}
        </td>
        <td>
          {this.props.proba.varstaMinima}
        </td>
        <td>
          {this.props.proba.varstaMaxima}
        </td>
        <td>
          <button onClick={this.handleClick}>
            Delete
          </button>
        </td>
      </tr>
    )
  }
}

class ProbaTable extends React.Component{
  render(){
    var rows = [];
    var functieStergere = this.props.delFunc

    this.props.probe.forEach(function(proba){
      rows.push(<ProbaRow proba={proba} key={proba.id} delFunc={functieStergere} />)
    })

    return(
      <div className="ProbaTable">

        <table className="center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Descriere</th>
              <th>Varsta minima</th>
              <th>Varsta maxima</th>
            </tr>
          </thead>
          <tbody>{rows}</tbody>
        </table>

      </div>
    );
  }
}

export default ProbaTable