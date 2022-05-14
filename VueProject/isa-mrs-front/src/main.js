import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCompositionAPI from '@vue/composition-api'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSpinner, faAngleDown, faUser, faRightFromBracket } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/src/jquery.js'
import 'bootstrap/dist/js/bootstrap.min.js'
import  './assets/style.css'

Vue.use(VueCompositionAPI)
library.add(faSpinner, faAngleDown, faUser, faRightFromBracket)
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
