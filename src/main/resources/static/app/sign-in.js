Vue.use(vuelidate.default)

Vue.component("sign-in", {
    template: `
    <div class="signIn">
        <guest-navbar></guest-navbar>
        <div class="app">
            <div class="vertical-center">
                <div class="inner-block">
                    <div class="signIn">
                        <form @submit.prevent>
                            <h3>Sign In</h3>
                            
                            <div class="form-group required">
                                <label class="control-label">E-mail </label>
                                <input v-model="form.email" type="email" class="form-control form-control-lg" placeholder="Please enter your email...">
                            </div>
                            
                            <div class="form-group required">
                                <label class="control-label">Password </label>
                                <input v-model="form.password" type="password" class="form-control form-control-lg" placeholder="Please enter your password..." pattern="/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/">
                            </div>
                            
                            <div class="form-group">
                                <button @click.native="login" @enter.native="login" :disabled="$v.form.$invalid" type="submit" class="btn btn-dark btn-lg btn-block">Sign In</button>
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
                password: ""
            }
        }
    },
    methods: {
        signIn() {
            axios.post("/sign-in/", {
                email: this.form.email,
                password: this.form.password
            }).then(response => {
                window.sessionStorage.setItem("user", JSON.stringify(response.data));
            }).catch(error => {
                alert("Incorrect login details")
                console.log("Ne ide brt")
            });
        }
    },
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
            }
        }
    }
})