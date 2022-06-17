<template>
    <div>
        <NavbarUser></NavbarUser>
        <br><br>
        <form class="w-50 mx-auto mt-5">
            <h2> New retreat </h2>
                <div class="form-group mb-3">
                    <label>Name</label>
                    <input v-model="form.name" type="text" class="form-control" placeholder="Name is required."/>
                </div>
                <div class="form-group mb-3">
                    <label>Description</label>
                    <textarea v-model="form.description" type="text" class="form-control" placeholder="Description is required."/>
                </div>
                <div class="form-group mb-3">
                    <label>Country</label>
                    <input v-model="form.country" type="text" class="form-control" placeholder="Country is required, and it starts with first capital letter."/>
                </div>
                <div class="form-group mb-3">
                    <label>City</label>
                    <input v-model="form.city" type="text" class="form-control" placeholder="City is required, and it starts with first capital letter."/>
                </div>
                <div class="form-group mb-3">
                    <label>Address</label>
                    <input v-model="form.address" type="text" class="form-control" placeholder="Address is required."/>
                </div>
                <div class="form-group mb-3">
                    <label>Number of rooms</label>
                    <input v-model.number="form.numOfRooms" type="number" class="form-control" placeholder="Number of rooms is required, and it must be number greater than 0."/>
                </div>
                <div class="form-group mb-3">
                    <label>Number of beds</label>
                    <input v-model.number="form.numOfBeds" type="number" class="form-control" placeholder="Number of beds is required, and it must be number greater than 0."/>
                </div>
                <div class="form-group mb-3">
                    <label>Price</label>
                    <input v-model.number="form.price" type="number" class="form-control" placeholder="Price is required, and it must be number greater than 0."/>
                </div>
                <div class="form-group mb-3">
                    <label>Rules of conduction:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Inside smoking disallowed"/>
                        <label>Inside smoking disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Pet friendly"/>
                        <label>Pet friendly</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Parties disallowed"/>
                        <label>Parties disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Furniture braking is charged extra"/>
                        <label>Furniture braking is charged extra</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Additional services:</label>
                    <br>
                    <template v-for="tag in tags">
                        <div class="form-group mb-3" :key="tag.id">
                            <input type="checkbox" v-model="form.additionalServices" :value="tag.name" :key="tag.id"/>
                            <label :for="tag.key">{{tag.name}}</label>
                        </div>
                    </template>
                </div>
                <div class="form-group mb-3">
                    <label>Pictures:</label>
                    <input accept="image/*" type="file" class="form-control" @change="addPicture($event)" multiple/>
                </div>
                <div class="form-group mb-3">
                    <button @click="addRetreat" type="submit" class="btn btn-primary float-end">Add retreat</button>
                </div>
        </form>
    </div>
</template>

<script>
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import NavbarUser from '@/components/NavbarUser.vue'

    Vue.use(VueAxios, axios)

    export default {
        name: "AddRetreat",
        components: {
            NavbarUser
        },
        data() {
            return {
                form: {
                    name: null,
                    description: null,
                    country: null,
                    city: null,
                    address: null,
                    numOfRooms: null,
                    numOfBeds: null,
                    price: null,
                    rulesOfConduction: [],
                    additionalServices: [],
                    pictures: []
                },
                tags: []
            }
        },
        computed: {
            nameIsValid() {
                return !!this.form.name;
            },
            descriptionIsValid() {
                return !!this.form.description;
            },
            countryIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.country) && !!this.form.country;
            },
            cityIsValid() {
                return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.city) && !!this.form.city;
            },
            addressIsValid() {
                return !!this.form.address;
            },
            roomsIsValid() {
                return typeof this.form.numOfRooms === 'number' && !!this.form.numOfRooms && this.form.numOfRooms > 0;
            },
            bedsIsValid() {
                return typeof this.form.numOfBeds === 'number' && !!this.form.numOfBeds && this.form.numOfBeds > 0;
            },
            priceIsValid() {
                return typeof this.form.price === 'number' && !!this.form.price && this.form.price > 0;
            },
            formIsValid() {
                return this.nameIsValid && this.descriptionIsValid && this.countryIsValid && this.cityIsValid && this.addressIsValid && this.roomsIsValid && this.bedsIsValid && this.priceIsValid;
            }
        },
        methods: {
            addRetreat() {
                if (this.formIsValid) {
                    this.sendData();
                }
            },
            sendData() {
                axios.post("http://localhost:8088/retreats/create-retreat", {
                    name: this.form.name,
                    description: this.form.description,
                    country: this.form.country,
                    city: this.form.city,
                    street: this.form.address,
                    rulesOfConduct: this.form.rulesOfConduction,
                    pictures: this.form.pictures,
                    numOfRooms: this.form.numOfRooms,
                    numOfBeds: this.form.numOfBeds,
                    price: this.form.price,
                    additionalServices: this.form.additionalServices
                },
				{
					headers: {
						Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
					}
				}).then(response => {
                    alert('Added retreat: ' + response.data.name + '.');
                    router.push('/profile-page-retreat-owner');
                }).catch(error => {
                    if (error.response.status === 400) alert("Server error.");
                    else alert("Error occurred while adding retreat!");
                });
            },

            addPicture(e) {
                let files = e.target.files || e.dataTransfer.files;
                if (!files.length)
                    return;
                for (let file of files) this.form.pictures.push(file.name);
            }
        },

        mounted() {
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                axios.get("http://localhost:8088/tags/retreat", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {this.tags = response.data});
            }
            else {
                router.push("/");
            }
        }
    }
</script>