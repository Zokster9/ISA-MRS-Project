<template>
    <div>
    <NavbarUser></NavbarUser>
    <br><br>
    <form class="w-50 mx-auto mt-5">
            <h2> Retreat info </h2>
                <div class="form-group mb-3">
                    <label>Name</label>
                    <input v-model="form.name" type="text" class="form-control"/>
                    <p v-if="!nameIsValid" class="alert alert-danger">Name is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Description</label>
                    <textarea v-model="form.description" type="text" class="form-control"/>
                    <p v-if="!descriptionIsValid" class="alert alert-danger">Description is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Country</label>
                    <input v-model="form.country" readonly type="text" class="form-control"/>
                    <p v-if="!countryIsValid" class="alert alert-danger">Country is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>City</label>
                    <input v-model="form.city" readonly type="text" class="form-control"/>
                    <p v-if="!cityIsValid" class="alert alert-danger">City is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Address</label>
                    <input v-model="form.address" readonly type="text" class="form-control"/>
                    <p v-if="!addressIsValid" class="alert alert-danger">Address is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of rooms</label>
                    <input v-model.number="form.numOfRooms" type="number" class="form-control"/>
                    <p v-if="!roomsIsValid" class="alert alert-danger">Number of rooms is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Number of beds</label>
                    <input v-model.number="form.numOfBeds" type="number" class="form-control"/>
                    <p v-if="!bedsIsValid" class="alert alert-danger">Number of beds is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Price</label>
                    <input v-model.number="form.price" type="number" class="form-control"/>
                    <p v-if="!priceIsValid" class="alert alert-danger">Price is required, and it must be number greater than 0.</p>
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
                    <button @click="changeRetreat" type="submit" class="btn btn-primary float-end">Update retreat</button>
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
                retreat: null,
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
            changeRetreat() {
                if (this.formIsValid) {
                    this.sendData();
                }
            },
            sendData() {
                axios.put("http://localhost:8088/retreats/update-retreat/" + this.$route.params.id, {
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
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("accessToken")
					}
				}).then(() => {
                    alert("Retreat updated!");
                    router.push("/service-crud");
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for this retreat exists.");
                    else alert("Error occured. Retreat has not been updated.");
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
            if (window.sessionStorage.getItem('role') === "ROLE_retreatOwner") {
                axios.get("http://localhost:8088/retreats/get/" + this.$route.params.id, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {
                    this.retreat = response.data;
                    this.form.name = response.data.name;
                    this.form.description = response.data.description;
                    this.form.country = response.data.country;
                    this.form.city = response.data.city;
                    this.form.address = response.data.street;
                    this.form.numOfRooms = response.data.numOfRooms;
                    this.form.numOfBeds = response.data.numOfBeds;
                    this.form.price = response.data.price;
                    this.form.rulesOfConduction = response.data.rulesOfConduct;
                    this.form.additionalServices = response.data.additionalServices;
                    this.form.pictures = response.data.pictures;
                });
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