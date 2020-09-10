window.axios = require('axios');
import Vue from 'vue'
import store from '~/store'
import router from '~/router'
import i18n from '~/plugins/i18n'
import App from '~/components/App'
window.moment = require('moment');

import '~/plugins'
import '~/components'

import SmallLoader from '~/components/small_loader'

import axios from 'axios'
import VueAxios from 'vue-axios'
import vSelect from 'vue-select'

Vue.component('small-loader', SmallLoader)

// import VueSocketio from 'vue-socket.io';


// import antiscroll from 'antiscroll';
// var io = require('socket.io-client/dist/socket.io.js');

// const SocketInstance = io.connect('http://fantasy.bassbelieveit.com:3000');
// const SocketInstance = io.connect('http://fantasy.test:8890');

Vue.component('v-select', vSelect)
// Vue.component('vue-select', vm)
// import Dropzone from './pages/dropzone'
// Vue.component('dropzone', Dropzone);

// var socket = io('https://paivms.com:3000');
// Vue.use(VueSocketio, socket)

Vue.use(VueAxios, axios)
// Vue.use(new VueSocketio({
//     debug: true,
//     connection: SocketInstance
// }));

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  i18n,
  store,
  router,
  ...App
})
