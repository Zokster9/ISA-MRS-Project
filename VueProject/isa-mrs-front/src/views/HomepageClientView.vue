<template>
    <div id="home-page-div">
      <NavbarClient></NavbarClient>
      <div class="container" style="margin-top:130px;">
        <div class="homepage">
          <h1>Welcome to your homepage!</h1>
          <h5>From here you can navigate to your profile, see all our services, check your subscriptions or any of our other options</h5>
        </div>
        <div class="row justify-content-evenly" style="margin-top: 100px;">
            <div class="col align-middle">
                <router-link exact to="/retreats" tag="div" class="card mx-2">
                    <img src="@/assets/retreat.png" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Retreats</h5>
                    <p class="card-text">Click here to browse and see all our beautiful retreats!</p>
                  </div>
                </router-link>
            </div>
            <div class="col align-middle">
                <router-link exact to="/ships" tag="div" class="card mx-2">
                <img src="@/assets/boat.png" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Ships</h5>
                    <p class="card-text">Click here to browse and get a taste of many ships that we have on offer!</p>
                  </div>
                </router-link>
            </div>
            <div class="col align-middle">
                <router-link exact to="/fishing-instructors" tag="div" class="card mx-2">
                <img src="@/assets/fishingInstructor.png" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Fishing instructors</h5>
                    <p class="card-text">Click here to see all our licenced fishing instructors and choose the best one for you!</p>
                  </div>
                </router-link>
            </div>
        </div>
      </div>
    </div>
</template>

<script>
  import NavbarClient from '@/components/NavbarClient.vue'
  import router from '@/router'

  export default {
    name: 'HomepageClient',
    components: {
      NavbarClient,    
    },
    mounted() {
      if (window.sessionStorage.getItem("role") != "ROLE_client") {
        router.push("/");
      }
    }
  }
</script>