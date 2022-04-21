Vue.use(vuelidate.default)

//const isCapitalFirstLetter = (value) => RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(value);

Vue.component("change-info", {
    template: `
    <div class="signUp">    
        <div class="app">
            <div class="vertical-center" style="margin-top: 85px">
                <div class="inner-block">
                    <div class="signUp">
                        <form @submit.prevent>
                            <h3>Change your info:</h3>
                            
                            <div class="form-group required">
                                <label>Email </label>
                                <input v-model="user.email" placeholder="Please enter your email..." readonly type="email" class="form-control form-control-lg">
                            </div>             
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="name">Name </label>
                                            <input v-model="user.name" id="name" type="text" placeholder="Name..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="surname">Surname </label>
                                            <input v-model="user.surname" id="surname" type="text" placeholder="Surname..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label>Address </label>
                                <input v-model="user.address" type="text" placeholder="Please enter your address..."  class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group required">
                                <table>
                                    <tr>
                                        <td>
                                            <label for="city">City </label>
                                            <input v-model="user.city" id="city" type="text" placeholder="City..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                        <td>
                                            <label for="country">Country </label>
                                            <input v-model="user.country" id="country" type="text" placeholder="Country..." pattern="[a-zA-Z\.]+$" class="form-control form-control-lg"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            
                            <div class="form-group required">
                                <label>Phone number </label>
                                <input v-model="user.phoneNumber" type="text" placeholder="Please enter your phone number..." class="form-control form-control-lg">
                            </div>
                            
                            <div class="form-group">
                                <button @click="changeInfo"  :disabled="$v.user.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Change info</button>
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
            user:{
                email: "",
                name: "",
                surname: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: ""
            }
        }
    },
    methods: {
        changeInfo() {
            axios.post("/users/changeInfo", {
                email: this.user.email,
                name: this.user.name,
                surname: this.user.surname,
                addressDTO: {
                    country: this.user.country,
                    city: this.user.city,
                    street: this.user.address,
                },
                phoneNumber: this.user.phoneNumber,
            }).then((response) => {
                alert("Successfully changed personal information!");
                //TODO: U odnosu na jwt bacati na odredjenu stranicu
                router.push("/profile-page-fishing-instructor")
            }).catch(function (error) {
                alert("Something went wrong.")
            });
        }
    },
    validations: {
        user: {
            email : {
                required: validators.required,
                minLength: validators.minLength(3),
                maxLength: validators.maxLength(50),
                mail : validators.email
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
    },
    mounted() {
        axios.get("users/findByEmail/lordje@gmail.com").then((response) => {
            this.user.name = response.data.name
            this.user.surname = response.data.surname
            this.user.email = response.data.email
            this.user.address = response.data.street
            this.user.country = response.data.country
            this.user.city = response.data.city
            this.user.phoneNumber = response.data.phoneNumber
        })
    },
})