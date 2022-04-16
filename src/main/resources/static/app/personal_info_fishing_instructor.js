Vue.component("personal-info-fishing-instructor", {
    props: ["user"],
    //TODO: CENTRIRAJ GA U SREDINU, NESTO NE VALJDA U PRVA DVA DIVA
    template:`
    <div class="d-flex flex-column">
        <div class="justify-content-center">
            <div class="card mb-3 mt-5" style="max-width: 18rem;"> 
                <div class="card-body"> 
                    <h5 class="card-title"> Welcome, {{ user.name }}  {{ user.surname }} </h5>
                    <h6 class="card-subtitle mb-2 text-muted">Preview of personal info: </h6>
                    <p class="card-text"> Email address: {{ user.email }} <br> Address: {{ user.country }}, {{ user.city }}, {{ user.street }}
                    <br> Loyalty status: {{ user.loyaltyStatus }} <br> Loyalty points: {{ user.loyaltyPoints }} </p>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 18rem;"> 
                <div class="card-body"> 
                    <h5 class="card-title"> Change personal info </h5>
                    <p class="card-text"> Edit your personal info you have entered when registering. </p>
                    <router-link to="#" class="btn btn-primary"> Edit personal info </router-link>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 18rem;"> 
                <div class="card-body"> 
                    <h5 class="card-title"> Change your password </h5>
                    <p class="card-text"> Change your current password with a new one. </p>
                    <router-link to="#" class="btn btn-primary"> Change password </router-link>
                </div>
            </div>
            <div class="card mb-3 border-danger" style="max-width: 18rem;"> 
                <div class="card-body"> 
                    <h5 class="card-title"> Account termination </h5>
                    <p class="card-text"> Once you terminate your account, you won't be able to have access to it ever again! </p>
                    <router-link to="#" class="btn btn-danger"> Terminate account </router-link>
                </div>
            </div>
        </div>
    </div>
    `,
})