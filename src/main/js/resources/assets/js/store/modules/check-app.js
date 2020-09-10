import * as types from '../mutation-types'
import axios from 'axios'

// state
export const state = {
  app_version: null
}

// getters
export const getters = {
  app_version: state => state.app_version,
}

// mutations
export const mutations = {
  SET_APP_VERSION (state, {data}) {
    var appVersion = JSON.parse(window.localStorage.getItem('app_version'))
    if (appVersion) {
      if (data !== appVersion) {
        window.localStorage.setItem('app_version', JSON.stringify(data))
        window.reload(true)
      }
    } else {
      window.localStorage.setItem('app_version', JSON.stringify(data))
    }
  }
}

// actions
export const actions = {
  async setAppVersion ({ commit }) {
//     try {
// //      const { data } = await axios.post('/api/checkAppVersion')
//       // console.log('app-versionnnnn')
//       // console.log(data)

//       // var appVersion = JSON.parse(window.localStorage.getItem('app_version'))
//       if (appVersion) {
//         // if (parseInt(data) !== parseInt(appVersion)) {
//           // window.localStorage.setItem('app_version', JSON.stringify(data))
//           // window.reload(true)
//         // }
//       } else {
//         // window.localStorage.setItem('app_version', JSON.stringify(data))
//       }

//       // commit(SET_APP_VERSION, { data: data })
//     } catch (e) {
//       console.log(e)
//       // commit(types.FETCH_USER_FAILURE)
//     }
  },
}