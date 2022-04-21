Vue.use(vuelidate.default)

Vue.component("personal-info-user", {
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
                <button v-if="!isChangingPassword" class="btn btn-primary" @click="isChangingPassword = true"> Change password </button>
                <input v-if="isChangingPassword" type="password" class="form-control mb-1" v-model="passwordChange.oldPassword" placeholder="Enter old password: ">
                <input v-if="isChangingPassword" type="password" class="form-control mb-1" v-model="passwordChange.newPassword" placeholder="Enter new password: ">
                <input v-if="isChangingPassword" type="password" class="form-control mb-2" v-model="passwordChange.confirmNewPassword" placeholder="Confirm new password: ">
                <button v-if="isChangingPassword" @ :disabled="$v.passwordChange.$invalid" class="btn btn-success" @click="changePassword"> Confirm new password</button>
                <button v-if="isChangingPassword" class="btn btn-primary" @click="isChangingPassword = false"> Back </button>
            </div>
        </div>
        <div class="card mb-3 border-danger" style="max-width: 18rem;"> 
            <div class="card-body text-danger"> 
                <h5 class="card-title"> Account termination </h5>
                <p class="card-text"> Once you terminate your account, you won't be able to have access to it ever again! </p>
                <button v-if="!isTerminating" class="btn btn-danger" @click="isTerminating = true"> Terminate account </button>
                <textarea v-if="isTerminating" v-model="terminationReason" placeholder="Enter the reason for termination:"></textarea>
                <button v-if="isTerminating" @ :disabled="$v.terminationReason.$invalid" class="btn btn-danger" @click="deleteAccount"> Confirm termination</button>
                <button v-if="isTerminating" class="btn btn-primary" @click="isTerminating = false"> Back </button>
            </div>
        </div>
    </div>
    `,
    data(){
        return {
            isTerminating: false,
            terminationReason: "",
            isChangingPassword: false,
            passwordChange:{
                oldPassword: "",
                newPassword: "",
                confirmNewPassword: ""
            },
        }
    },
    validations:{
        terminationReason: {
            required: validators.required,
        },
        passwordChange: {
            oldPassword:{
                //TODO: oldPassword mora biti isto sto i user.password
                required : validators.required,
            },
            newPassword:{
                required : validators.required
            },
            confirmNewPassword: {
                required : validators.required,
                sameAsPassword: validators.sameAs('newPassword')
            }

        }
    },
    //TODO: Dobavi Email iz jwt-a
    methods: {
        deleteAccount(){
            axios.post("/users/sendTerminationReason?email=lordje@gmail.com&terminationReason=" + this.terminationReason).then((response)=>{
                router.push("/main-screen")
                alert("Napisan razlog");
            })
        },
        changePassword(){
            axios.post("/users/changePassword?email=lordje@gmail.com&oldPassword=" + this.oldPassword + "&newPassword=" + this.newPassword).then((response)=>{
                router.push("/profile-page-fishing-instructor")
            }).catch(error => {
                alert("Old password is incorrect!")
            })
        }
    }
})