<template>
<div>
<button v-on:click="openCreateView()">New</button>
</div>

 <div class="container">
    <div class="blog">
      <div class="note-list">
        <div class="note-item" v-for="(item,index) in notebooks" 
        :key="index" 
        v-on:click="showContent(index)">
        {{item.title}}
        <span v-on:click="showUpdate(index)" class="update">modify</span>|
        <span v-on:click="deleteNote(index)" class="delete">delete</span>
        </div>
      </div>
      <div class="blog-content">
        <div class="content">
          <span v-html="content" ref="noteContent"></span>
        </div>
        <div class="menu-container">
          <div class="menu-item">
          size
          <button v-on:click="scaleFontSize()">+</button>
          <button  v-on:click="narrowFontSize()">-</button>
          </div>
          <div class="menu-item" >font
            <select @change="changeFontFamily()" v-model="selectedFontFamily">
              <option value="">select font...</option>
              <option v-for="(item,index) in fontFamily" :value="item.value" :key="index" >{{item.label}}</option>
            </select>
          </div>
          <div class="menu-item">add picture</div>
          <div class="menu-item"></div>
          <div class="menu-item">share</div>
        </div>
      </div>
       <div v-if="creationVisible" class="creation-panel">
            <h3>title</h3>
            <input type="text" v-model="inputTitle" />
            <h3>content</h3>
            <textarea class="textbox" cols="30" rows="10" v-model="inputContent"></textarea>
            <button @click="isCreation ? addNote() : updateNote()">submit</button>
        </div>
    </div>
    <div class="footer">
      <div class="footer-menu">notebook</div>
      <div class="footer-menu">share page</div>
    </div>
   
  </div>
</template>
 
<script>
import { reactive, toRefs } from "vue";
import { getCurrentInstance, onMounted } from "@vue/runtime-core";
export default {
  name: 'NoteBook',
  setup() {
    let currentInstance = '';
    onMounted(()=> {
      currentInstance = getCurrentInstance()
    });
    const state = reactive({
      inputTitle: "",
      notebooks: [{title:'title1',content:'content1'}], 
      inputContent: "",
      content: '',
      creationVisible: false,
      isCreation: true,
      modifiedIndex: 0,
      fontSize: 20,
      selectedFontFamily: "",
      fontFamily:[{label:'Song font ',value:"宋体"},{label:'Kai font',value:"楷体"}]
    });
    const openCreateView = () => {
      state.creationVisible = true
    };
    const deleteNote = (index) => {
      console.log(index)
      console.log(state.notebooks)
      state.notebooks.splice(index, 1)
      state.content = ''
    };
    const showUpdate = (index) => {
      state.creationVisible = true
      state.isCreation = false
      state.modifiedIndex = index
      let note = state.notebooks[index]
      state.inputTitle = note.title
      state.inputContent = note.content
    };
    const updateNote = () => {
      state.notebooks[state.modifiedIndex] = {title:state.inputTitle, content: state.inputContent}
      state.modifiedIndex = 0
      state.creationVisible = false
      state.inputTitle = "";
      state.inputContent = "";
      state.isCreation = true
    };
    const addNote = () => {
      state.isCreation = true
      state.notebooks.push({title:state.inputTitle, content:state.inputContent});
      state.inputTitle = "";
      state.inputContent = "";
      state.creationVisible = false
    };
    const deleteText = (index) => {
      state.notebooks.splice(index, 1);
    };
    const deleteAll = () => {
      state.notebooks = [];
      state.count = 0;
    };
    const showContent = (index) => {
      let note = state.notebooks[index]
      state.content = note.title + '</br>' + note.content
    };
    const scaleFontSize = () => {
      if(state.fontSize < 40) {
        state.fontSize += 5
      }
      modifyFontSize()
    };
    const narrowFontSize = () => {
      if(state.fontSize > 5) {
        state.fontSize -= 5
      }
      modifyFontSize()
    };
    const modifyFontSize = () => {
      currentInstance.ctx.$refs.noteContent.style.fontSize = state.fontSize + 'px'
    };
    const changeFontFamily = () => {
      currentInstance.ctx.$refs.noteContent.style.fontFamily = state.selectedFontFamily 
    };
    return {
      ...toRefs(state),
      addNote,
      deleteText,
      deleteAll,
      openCreateView,
      deleteNote,
      updateNote,
      showContent,
      showUpdate,
      modifyFontSize,
      narrowFontSize,
      scaleFontSize,
      changeFontFamily,
    };
  },
};
</script>
 
<style scoped>
.container {
  width: 1280px;
  height: 720px;
  /*margin-left: 300px;*/
}

.blog {
  height: 100%;
  display: flex;
  flex-direction: row;
  border-width: 2px;
  border-style: solid;
  border-color: aqua;
}


.note-list{
  flex: 1;
  border-width: 2px;
  border-style: solid;
  border-color: rgb(151, 46, 192);
}

.note-list .note-item {
  border-width: 2px;
  border-style: solid;
  border-color: rgb(46, 192, 46);
  margin-bottom: 5px;
  text-align: center;
}

.blog-content {
  flex: 3;
  border-width: 2px;
  border-style: solid;
  border-color: rgb(192, 26, 26);
}

.blog-content .content {
  height: 95%;
}

.creation-panel {
  flex: 1;
  border-width: 2px;
  border-style: solid;
  border-color: rgb(192, 26, 26);
}

.menu-container {
  height: 5%;
  display: flex;
}

.menu-container .menu-item {
  flex: 1;
  border-width: 2px;
  border-style: solid;
  border-color: rgb(224, 189, 29);
  text-align: center;
}

.footer {
  display: flex;
}

.footer-menu {
  flex: 1;
  border-width: 2px;
  border-style: solid;
  border-color: rgb(192, 26, 26);
  text-align: center;
}
.delete {
  color: red;
}
.update {
  color: blue;
}
</style>