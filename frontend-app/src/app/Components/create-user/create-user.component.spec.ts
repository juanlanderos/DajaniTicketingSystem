import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateUserComponent } from './create-user.component';

describe('CreateUserComponent', () => {
  //Arranging
  let component: CreateUserComponent; //creating a component of type createUserComponent
  let fixture: ComponentFixture<CreateUserComponent>; //creatign a fixture of type componentFixture

  beforeEach(async () => { //run async in order to let all acyncronous code finish before continuing
    //configuring modules
    //module - import components, providers.. etc.
    await TestBed.configureTestingModule({
      declarations: [ CreateUserComponent ]
    })
    .compileComponents();
    //Act
    fixture = TestBed.createComponent(CreateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  /*
    //Act (Actions)
    Will not execute untilBeforeEach is finished running
  */
  it('should create', () => {
    //Asserting (Checking Data)
    expect(component).toBeTruthy();
  });
});

/*
fdescribe ('CreateUserComponent', () => {
  it('Create User Functionality', () => {
    const tax = 10;
    expect(tax).toBe(10);
  });
});
*/

/*

1. Import required classes and interfaces
async
TestBed
ComponentFixture

@angular-core

2, import Components required for testing 
  - for which we are writing our tests

3. describe ('component name', () => {

  })

4. Inside the describe function, we will have it functions

  describe ('component name', () => {

    it ('test case #1, () => {

    })
  })

5. We create a Fixture for component and template.
  - What is a fixture? 
    - nothing but a wrapper class around the component and template
    - using fixture we can get properties of component and template
  
6. BeforeEach -> method -> 
  - before running test scripts - we need some ground work
    - setting up component
    - setting up services
    - importing services etc etc..

7. BeforeEach =
  - why are there 2 beforeEach?
  - what does each do?
  - what can be done in beforeEach... 

8. TesBed - main utility to define our module, components, etc.. 

*/