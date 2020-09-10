<template>
  <div class="row" style="margin-top:15px;">
    <div class="col-lg-8 m-auto mt-5">
      <card :title="$t('login')">
          <div class="form-group row">
            <label class="col-md-3 col-form-label text-md-right">{{ $t('email') }}</label>
            <div class="col-md-7">
              <input v-model="username" class="form-control" name="email">
              <!-- <has-error :form="form" field="email"/> -->
            </div>
          </div>
          <!-- Password -->
          <div class="form-group row">
            <label class="col-md-3 col-form-label text-md-right">{{ $t('password') }}</label>
            <div class="col-md-7">
              <input v-model="password" class="form-control" type="password" name="password">
              <!-- <has-error :form="form" field="password"/> -->
            </div>
          </div>
          <!-- Remember Me -->
          <div class="form-group row">
            <div class="col-md-3"/>
            <div class="col-md-7 d-flex">
              <checkbox v-model="remember" name="remember">
                {{ $t('remember_me') }}
              </checkbox>

              <router-link :to="{ name: 'password.request' }" class="small ml-auto my-auto">
                {{ $t('forgot_password') }}
              </router-link>
            </div>
          </div>

          <div class="form-group row">
            <div class="col-md-7 offset-md-3 d-flex">
              <!-- Submit Button -->
              <button @click="loginUser">
                Log In
              </button>

              <!-- GitHub Login Button -->
              <login-with-github/>
            </div>
          </div>
      </card>
    </div>
  </div>
</template>

<script>
import Form from 'vform'
import LoginWithGithub from '~/components/LoginWithGithub'

export default {
  middleware: 'guest',

  components: {
    LoginWithGithub
  },

  metaInfo () {
    return { title: this.$t('login') }
  },

  data: () => ({
    username:'',
    password:'',
    remember: false
  }),

  methods: {
    loginUser: function() {

      var that = this

      var postData = {
        username: this.username,
        password: this.password
      }

      console.log(postData)

      axios.post("/api/login", postData).then(response =>{
      // Save the token.
        this.$store.dispatch('auth/saveToken', {
          token: response.data,
          remember: this.remember
        })
        // Fetch the user.
        that.$store.dispatch('auth/fetchUser')
        // Redirect home.
        that.$router.push("/")
      })
    }
  } 
}
</script>
