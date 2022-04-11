Vue.use(vuelidate.default)

Vue.component("sign-up", {
    template: `
    <div class="signUp">
        <guest-navbar></guest-navbar>
        <div class="app">
            <div class="vertical-center" style="margin-top: 80px">
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
                            
                            <div class="form-group">
                                <label>Email</label>
                                <input v-model="form.email" type="email" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <label>Password</label>
                                <input v-model="form.password" type="password" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <label>Confirm password</label>
                                <input v-model="form.confirmPassword" type="password" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="name">Name</label>
                                            <input v-model="form.name" id="name" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="surname">Surname</label>
                                            <input v-model="form.surname" id="surname" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group">
                                <label>Address</label>
                                <input v-model="form.address" type="text" class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="city">City</label>
                                            <input v-model="form.city" id="city" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="country">Country</label>
                                            <input v-model="form.country" id="country" type="text" pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group">
                                <label>Phone number</label>
                                <input v-model="form.phoneNumber" type="text" class="form-control form-control-lg">
                            </div>
                            
                            <select v-if="isPrivilegedUser" v-model="form.privilegedUserType" class="form-select" aria-label="Privileged user types">
                              <option selected value="retreatOwner">Retreat owner</option>
                              <option value="shipOwner">Ship owner</option>
                              <option value="fishingInstructor">Fishing instructor</option>
                            </select>
                            
                            <div v-if="isPrivilegedUser" class="form-group">
                                <label>Registration explanation</label>
                                <textarea v-model="form.registrationExplanation" class="form-control form-control-lg"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <button @click.native="login" @enter.native="login" :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Sign Up</button>
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
            return this.form.registrationType === "privilegedUser"
        }
    },
    methods: {},
    validations: {
        form: {
            email : {
                required: validators.required,
                minLength: validators.minLength(3),
                maxLength: validators.maxLength(50)
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
                maxLength: validators.maxLength(30)
            },
            surname : {
                required : validators.required,
                minLength : validators.minLength(1),
                maxLength: validators.maxLength(30)
            },
            address: {
                required : validators.required,
                minLength : validators.minLength(1),
            },
            city: {
                required : validators.required,
                minLength : validators.minLength(1),
            },
            country: {
                required : validators.required,
                minLength : validators.minLength(1),
            },
            phoneNumber: {
                required : validators.required,
                minLength : validators.minLength(12),
            },
            registrationExplanation: {
                required : validators.requiredIf(() => {
                    return this.isPrivilegedUser
                }),
                minLength : validators.minLength(1),
            }
        }
    },
})