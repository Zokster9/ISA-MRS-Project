<template>
    <div v-if="totalPages() > 0">
        <span v-if="showPreviousLink()" class="pagination-btn" v-on:click="updatePage(currentPage - 1)"><b> Back </b></span>
        {{currentPage + 1}} of {{ totalPages()}}
        <span v-if="showNextLink()" class="pagination-btn" v-on:click="updatePage(currentPage + 1)"><b> Forward </b></span>
    </div>
</template>
<script>
    export default{
        name:'PaginationComponent',
        props: ['elements', 'currentPage', 'pageSize'],
        methods:{
            updatePage(pageNumber){
                this.$emit('page:update', pageNumber);
            },
            totalPages(){
                return Math.ceil(this.elements.length/this.pageSize);
            },
            showPreviousLink(){
                return this.currentPage == 0 ? false : true;
            },
            showNextLink() {
                return this.currentPage == (this.totalPages() - 1) ? false : true;
            }
        }
    }
</script>
<style scoped>
    .pagination-btn{
        cursor: pointer;
    }
</style>