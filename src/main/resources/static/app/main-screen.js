Vue.component("main-screen", {
    template: `
    <div style="height: 100%; width: 100%; background-image: url('images/background.jpg'); background-size: cover">
        <guest-navbar></guest-navbar>
        <div class="container" style="margin-top: 80px;">
            <div class="align-items-center">
                <h1>L's RENTING APP</h1>
                <h4>Welcome to our renting website. Here you can find only the best retreats, ships and fishing instructors.</h4>
            </div>
            <div class="row justify-content-evenly" style="margin-top: 100px;">
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                        <img src="images/retreat.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Retreats</h5>
                        <p class="card-text">Click here to browse and see all our beautiful retreats!</p>
                      </div>
                    </router-link>
                </div>
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                    <img src="images/boat.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Ships</h5>
                        <p class="card-text">Click here to browse and get a taste of many ships that we have on offer!</p>
                      </div>
                    </router-link>
                </div>
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                    <img src="images/fishingInstructor.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Fishing instructors</h5>
                        <p class="card-text">Click here to see all our licenced fishing instructors and choose the best one for you!</p>
                      </div>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
    `,
});