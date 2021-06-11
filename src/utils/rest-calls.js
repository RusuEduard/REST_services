import {PROBE_BASE_URL} from './consts'

function status(response) {
  console.log('response status ' + response.status)
  if(response.status >= 200 && response.status < 300){
    return Promise.resolve(response)
  }  
  else{
    return Promise.reject(new Error(response.statusText))
  }
}

function json(response) {
  return response.json()
}


export function GetProbe(){
  var headers = new Headers();
  headers.append('Accept', 'application/json')

  var antet = {
    mode: 'cors',
    method: 'GET',
    headers: headers,
  }

  var request = new Request(PROBE_BASE_URL, antet)

  console.log('Inainte de fetch pentru ' + PROBE_BASE_URL)

  return fetch(request)
    .then(status)
    .then(json)
    .then(data=> {
        console.log('Request succeeded with JSON response', data)
        return data
    }).catch(error=>{
        console.log('Request failed', error)
        return error
    })
}

export function GetProbaId(id){
  var headers = new Headers()
  headers.append('Accept', 'application/json')

  var antet = {
    mode: 'cors',
    method: 'GET',
    headers: headers,
  }

  var get_url = PROBE_BASE_URL+'/'+id
  var request = new Request(get_url, antet)

  return fetch(request)
    .then(status)
    .then(json)
    .then(response=> {
        console.log('Request succeeded with JSON response', response)
        return response
    }).catch(error=>{
        console.log('Request failed', error)
        return error
    })
}


export function GetProbaDescriere(descriere){
  var headers = new Headers()
  headers.append('Accept', 'application/json')

  var antet = {
    mode: 'cors',
    method: 'GET',
    headers: headers,
  }

  var get_url = PROBE_BASE_URL+'/descriere/'+descriere
  var request = new Request(get_url, antet)

  return fetch(request)
    .then(status)
    .then(json)
    .then(response=> {
        console.log('Request succeeded with JSON response', response)
        return response
    }).catch(error=>{
        console.log('Request failed', error)
        return error
    })
}


export function DeleteProba(id) {
  console.log('inainte de fetch delete')
  
  var headers = new Headers()
  headers.append('Accept', 'application/json')

  

  var antet = {
    method: 'DELETE',
    headers: headers,
    mode: 'cors'
  }

  var probaDelUrl = PROBE_BASE_URL+'/'+id

  return fetch(probaDelUrl, antet)
    .then(status)
    .then(response =>{
        console.log('Delete status ' + response.status)
        return response.text()
    }).catch(error=>{
      console.log('error ' + error)
      return Promise.reject(error)
    })
}

export function AddProba(proba){
  console.log('inainte de fetch post ' + JSON.stringify(proba))

  var headers = new Headers()

  headers.append('Accept', 'application/json')
  headers.append('Content-Type', 'application/json')

  var antet = {
    method: 'POST',
    headers: headers,
    mode: 'cors',
    body: JSON.stringify(proba)
  }

  return fetch(PROBE_BASE_URL, antet)
    .then(status)
    .then(response =>{
      return response.text()
    }).catch(error=> {
      console.log('Request failed ', error)
      return Promise.reject(error)
    })
}

export function UpdateProba(id, proba){
  console.log('inainte de fetch post ' + JSON.stringify(proba))

  var headers = new Headers()

  headers.append('Accept', 'application/json')
  headers.append('Content-Type', 'application/json')

  var antet = {
    method: 'PUT',
    headers: headers,
    mode: 'cors',
    body: JSON.stringify(proba)
  }

  var url = PROBE_BASE_URL + '/' + id

  return fetch(url, antet)
  .then(status)
  .then(response =>{
    return response.text()
  }).catch(error=> {
    console.log('Request failed ', error)
    return Promise.reject(error)
  })
}