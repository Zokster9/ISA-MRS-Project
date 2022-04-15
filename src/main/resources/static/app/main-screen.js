Vue.component("main-screen", {
    template: `
    <div style="height: 100%; width: 100%">
        <guest-navbar></guest-navbar>
        <div class="container" style="margin-top: 80px;">
            <div class="align-items-center">
                <h1>SITE NAME</h1>
                <h4>Welcome to our renting website.</h4>
            </div>
            <div class="row justify-content-evenly" style="margin-top: 100px;">
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                        <img src="images/retreat.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                      </div>
                    </router-link>
                </div>
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                    <img src="images/boat.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                      </div>
                    </router-link>
                </div>
                <div class="col align-middle">
                    <router-link exact to="/sign-in" tag="div" class="card mx-2">
                    <img src="images/fishingInstructor.png" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                      </div>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
    `,
});
