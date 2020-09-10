<template>
  <div style="width:100%; float:left;">
    <!-- New Posts -->
    <!-- <div class="col-sm-12 col-md-6 float-left" style="padding:5px;">
      <card title="New Post">
        <div class="newPostDiv">
          <h4 class="wording"><i class="fa fa-plus"></i> Add Post</h4>
        </div>
      </card>
    </div> -->

    <!-- V-for on the posts that user has submitted -->
    <posts-container :posts="posts"></posts-container>
  </div>
</template>

<script>
import PostsContainer from './../../components/posts_container'
import Form from 'vform'
import { mapGetters } from 'vuex'

export default {
  scrollToTop: false,

  middleware: 'auth',

  metaInfo () {
    return { title: this.$t('settings') }
  },

  data: function () {
    return {
      posts: null
    }
  },

  computed: mapGetters({
    user: 'auth/user'
  }),

  components: {
    PostsContainer
  },

  created () {
    // Fill the form with user data.
    // this.form.keys().forEach(key => {
    //   this.form[key] = this.user[key]
    // })
    this.getPosts()
  },

  methods: {
    async update () {
      const { data } = await this.form.patch('/api/settings/profile')

      this.$store.dispatch('auth/updateUser', { user: data })
    },

    getPosts () {
      const postData = {
        user_id: this.user.id
      }
      console.log(postData)
      axios.post('/api/getPosts', postData).then(response => {
        if (response.status === 200) {
          console.log(response)
          this.posts = response.data
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.newPostDiv {
  width:100%;
  height:300px;
  text-align: center;


  .wording {
    color: #000;
    line-height: 200px;
  }

  &:hover {
    background-color: #ccc;
    text-align:center;
    line-height: 200px;
    color:#fff;
    cursor: pointer;

    .wording {
      display: block;
    }
  }
}

.postDiv {
  cursor: pointer;
  width:100%;
  height:300px;

  .insideInfo {
    width:100%;
    float:left;

    .label {
      text-align: right;
    }
  }
}
</style>
