Vue.use(vuelidate.default)

Vue.component("personal-info-fishing-instructor", {
    props: ["user"],
    template:`
    <div>
        <div class="card mb-3 border-primary mt-5" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Welcome, {{ user.name }}  {{ user.surname }} </h5>
                <h6 class="card-subtitle mb-2 text-muted">Preview of personal info: </h6>
                <p class="card-text"> Email address: {{ user.email }} <br> Address: {{ user.country }}, {{ user.city }}, {{ user.street }}
                <br> Loyalty status: {{ user.loyaltyStatus }} <br> Loyalty points: {{ user.loyaltyPoints }} </p>
            </div>
        </div>
        <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Change personal info </h5>
                <p class="card-text"> Edit your personal info you have entered when registering. </p>
                <router-link to="#" class="btn btn-primary"> Edit personal info </router-link>
            </div>
        </div>
        <div class="card mb-3 border-primary" style="max-width: 18rem;"> 
            <div class="card-body"> 
                <h5 class="card-title"> Change your password </h5>
                <p class="card-text"> Change your current password with a new one. </p>
                <router-link to="#" class="btn btn-primary"> Change password </router-link>
            </div>
        </div>
        <div class="card mb-3 border-danger" style="max-width: 18rem;"> 
            <div class="card-body text-danger"> 
                <h5 class="card-title"> Account termination </h5>
                <p class="card-text"> Once you terminate your account, you won't be able to have access to it ever again! </p>
                <button v-if="!isTerminating" class="btn btn-danger" @click="isTerminating = true"> Terminate account </button>
                <textarea v-if="isTerminating" v-model="terminationReason" placeholder="Enter the reason for termination:"></textarea>
                <button v-if="isTerminating" @ :disabled="$v.terminationReason.$invalid" class="btn btn-danger" @click="deleteAccount"> Confirm termination</button>
            </div>
        </div>
    </div>
    `,
    data(){
        return {
            isTerminating: false,
            terminationReason: ""
        }
    },
    validations:{
        terminationReason: {
            required: validators.required,
        }
    },
    //TODO: Dobavi Email iz jwt-a
    methods: {
        deleteAccount(){
            axios.post("/users/sendTerminationReason?email=lordje@gmail.com&terminationReason=" + this.terminationReason).then((response)=>{
                // TODO: izmeniti na pocetnu stranicu
                //window.location.assign("http:localhost:8088/#/");
                alert("Napisan razlog");
            })
        },
    }
})