document.addEventListener("DOMContentLoaded", function(event) {
    injectListeners()
    console.log('dupa')
  });

function injectListeners() {

    let addTaskButton = document.querySelector('#add-task')
    addTaskButton.addEventListener('click', turnOnAddTaskWindow)

    let closeAddTaskButton = document.querySelector('[data-close-button]')
    closeAddTaskButton.addEventListener('click', turnOffAddTaskWindow)

    let overlay = document.querySelector('#overlay')
    overlay.addEventListener('click', turnOffAddTaskWindow)

}

function turnOnAddTaskWindow()
{
    console.log("Turning on overlay + modal")
    document.querySelector('#overlay').classList.add('active')
    document.querySelector('.add-task').classList.add('active')
}

function turnOffAddTaskWindow()
{
    console.log("Turning off overlay + modal")
    document.querySelector('#overlay').classList.remove('active')
    document.querySelector('.add-task').classList.remove('active')
}
