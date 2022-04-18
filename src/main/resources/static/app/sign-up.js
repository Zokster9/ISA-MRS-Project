Vue.use(vuelidate.default)

const isCapitalFirstLetter = (value) => RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(value);

Vue.component("sign-up", {
    template: `
    <div class="signUp">
        
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <div class="signUp">
                        <form @submit.prevent>
                            <h3>Sign Up</h3>
                            <div class="form-check form-check-inline">
                              <input :checked="isClient" v-model="form.registrationType" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="client">
                              <label class="form-check-label" for="inlineRadio1">Client</label>
                            </div>
                            
                            <div class="form-check form-check-inline float-end">
                              <input v-model="form.registrationType" class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="privilegedUser">
                              <label class="form-check-label" for="inlineRadio2">Privileged User</label>
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Email </label>
                                <input v-model="form.email" placeholder="Please enter your email..." type="email" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Password </label>
                                <input v-model="form.password" placeholder="Please enter your password..." type="password" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Confirm password </label>
                                <input v-model="form.confirmPassword" placeholder="Please confirm your password..." type="password" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="name">Name </label>
                                            <input v-model="form.name" id="name" type="text" placeholder="Name..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label class="control-label" for="surname">Surname </label>
                                            <input v-model="form.surname" id="surname" type="text" placeholder="Surname..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Address </label>
                                <input v-model="form.address" type="text" placeholder="Please enter your address..." class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="city">City </label>
                                            <input v-model="form.city" id="city" type="text" placeholder="City..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label class="control-label" for="country">Country </label>
                                            <input v-model="form.country" id="country" type="text" placeholder="Country..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Phone number </label>
                                <input v-model="form.phoneNumber" type="text" placeholder="Please enter your phone number..." class="form-control form-control-lg">
                            </div>
                            
                            <div v-if="isPrivilegedUser" class="form-group required">
                                <label class="control-label">Privileged user type </label>
                                <select  v-model="form.privilegedUserType" class="form-select" aria-label="Privileged user types">
                                  <option selected value="retreatOwner">Retreat owner</option>
                                  <option value="shipOwner">Ship owner</option>
                                  <option value="fishingInstructor">Fishing instructor</option>
                                </select>
                            </div>
                            
                            <div v-if="isPrivilegedUser" class="form-group required">
                                <label class="control-label">Registration explanation </label>
                                <textarea v-model="form.registrationExplanation" placeholder="Please enter why you should be allowed to register..." class="form-control form-control-lg"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <button @click="Register"  :disabled="$v.form.$invalid || isExplanationRequired" type="submit" class="btn btn-dark btn-lg btn-block">Sign Up</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `,
    data () {
        return {
            form: {
                email: "",
                password: "",
                confirmPassword: "",
                name: "",
                surname: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: "",
                privilegedUserType: "retreatOwner",
                registrationExplanation: "",
                registrationType: "client",
            }
        }
    },
    computed: {
        isClient() {
            return this.form.registrationType === "client";
        },
        isPrivilegedUser() {
            return this.form.registrationType === "privilegedUser";
        },
        isExplanationRequired() {
            return this.isPrivilegedUser && this.form.registrationExplanation === "";
        }
    },
    methods: {
        Register() {
            axios.post("/users/register", {
                email: this.form.email,
                password: this.form.password,
                confirmPassword: this.form.confirmPassword,
                name: this.form.name,
                surname: this.form.surname,
                addressDTO: {
                    country: this.form.country,
                    city: this.form.city,
                    street: this.form.address
                },
                phoneNumber: this.form.phoneNumber,
                registrationType: this.form.registrationType,
                privilegedUserType: this.form.privilegedUserType,
                registrationExplanation: this.form.registrationExplanation
            }).then((response) => {
                alert("User notified");
            }).catch(function (error) {
                alert("Not working")
            });
        }
    },
    validations: {
        form: {
            email : {
                required: validators.required,
                minLength: validators.minLength(3),
                maxLength: validators.maxLength(50),
                mail : validators.email
            },
            password: {
                required: validators.required,
                minLength: validators.minLength(1)
            },
            confirmPassword : {
                required : validators.required,
                sameAsPassword : validators.sameAs('password')
            },
            name : {
                required : validators.required,
                minLength: validators.minLength(1),
                maxLength: validators.maxLength(30),
                isCapitalFirstLetter
            },
            surname : {
                required : validators.required,
                minLength : validators.minLength(1),
                maxLength: validators.maxLength(30),
                isCapitalFirstLetter
            },
            address: {
                required : validators.required,
                minLength : validators.minLength(1),
            },
            city: {
                required : validators.required,
                minLength : validators.minLength(1),
                isCapitalFirstLetter
            },
            country: {
                required : validators.required,
                minLength : validators.minLength(1),
                isCapitalFirstLetter
            },
            phoneNumber: {
                required : validators.required,
                minLength : validators.minLength(12),
            },
        }
    }
})