Vue.component("profile-page-client", {
    template: `
        <div class="d-flex flex-row">
            <div class="mx-auto">
                <personal-info-user v-if="user" :user="user"></personal-info-user>
            </div>
        </div>
    `,
    data() {
        return {
            user: null,
        }
    },
    //TODO:tu ce biti neki jwt za email
    mounted(){
        axios.get("users/findByEmail/lordje@gmail.com").then((response) =>{
            this.user = response.data
        })
    }
});