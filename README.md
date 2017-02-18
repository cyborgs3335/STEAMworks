# STEAMworks
The Programming project for Team 3335's 2017 robot.
## Updating software
### Imaging the RoboRio
ALL robots reguire an up to date roboRio

Go to Update Suite in order to image roboRio

[Download update suite](https://wpilib.screenstepslive.com/s/4485/m/13503/l/599670-installing-the-frc-2017-update-suite-all-languages)

after updatesuite installation, connect usb into the roborio usb port and connect it to the computer\(do not use an ethernet cable)

to open imaging tool which is downloaded with updatesuit go to **C:\Program Files (x86)\National Instruments\LabVIEW 2016\project\roboRIO Tool** and then click on **roboRIO_ImagingTool.exe**

After launching, the roboRIO Imaging Tool will scan for available roboRIOs and indicate any found in the top left box. The bottom left box will show the available image versions that may be loaded onto the roboRIO. The right hand column contains information and settings for the roboRIO selected in the top left pane.

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/309/346/original/508d4fe9-2e1c-4ac2-a852-7fdeaeba25dd.png?1484677916)

Choose to `Format Target` and select the latest image, then click reformat.

Imaging is complete if you see this:

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/309/341/original/c77ae106-441d-4811-b518-c72825ae5597.png?1484677907)

### RoboRio firmware

to get to the web dashboard you must first download silverlight/(refer to instructons below)

open internet explorer and in the search window enter 172.22.11.2 and click enter 

Click Login. Enter "admin" in the Username field and leave the Password field blank. Then click update firmware.

go to **c:\Program Files (x86)\National Instruments\Shared\Firmware\cRIO\76F2\ ** select the latest firmware and click open

after confirming that the firmware is correct click begin update

do not close the browser or power of the robot until the message appears "The firmware update completed successfully".

#### Downloading Silverlight
go to silver light home page

![Imgur](http://imgur.com/OJji8JB)

[link to silverlight website](https://www.microsoft.com/silverlight/)

download silverlight

enjoy silverlight






