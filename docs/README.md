# LPOO_35 - FarmVille

FarmVille was an agriculture-simulation social network game developed and published by Zynga in 2009.

In this text-based implementation, you can manage a farm, including its crop fields and buildings.
You can also go to the market where you can buy and sell items, a house where you can rest, and a 
warehouse where your items are stored.

In this exciting new version you need to pay attention to the weather. Your fields need water to produce
but if it rains too much, or a storm comes, you might lose some or even all of your hard work.

This project was developed by Diogo Costa (up201906731@edu.fe.up.pt), Pedro Gonçalo Correia (up201905348@edu.fe.up.pt) and Rui Alves (up201905853@edu.fe.up.pt) for LPOO 2020-21.


## IMPLEMENTED FEATURES

- **Movement** - The farmer may be controlled with the WSAD keys, moving
as long as there isn't any obstacle (building or fence) obstructing his
way.
- **Interaction with crop fields** - Buildings and crop fields have light brown paths to mark
interactive positions. Pressing the SPACE bar when the farmer is in one such
position will trigger an interaction with that building (typically opening a
popup menu). For now, only crop fields have an action associated.
- **Popup Menus** - Popup menus are UI elements with labels and buttons that may be
clicked with the mouse (and are highlighted when hovered with the mouse).
These popups do not occupy the whole screen, so it is still possible to see
part of the farm. When a popup is opened, time in the farm passes as normal.
Right now, the menus implemented are used to plant, remove or harvest crops.
- **Crop field** - The crop field is used to plant crops. Right now, only
wheat can be planted. After planting, the crop takes some time to grow, during
which the farmer may remove the crop if he changes his mind. The gradual growth
of the crop may be seen visually when looking at the crop field, as the crops
get bigger as the harvest time is approaching. When the crop is ready to harvest
the farmer may harvest it. However, nothing will be given in return for now, as
the inventory and warehouse are not yet implemented.
- **HUD** - At the bottom of the farm screen, a HUD bar is displayed where the current
time and weather may be seen.
- **Weather** - From time to time (by random chance based on the current weather),
the weather can change, with possible weathers being sunny, cloudy, rainy,
windstorm and thunderstorm. For now, even though the weather may be seen
in the HUD bar, it has no other game effect.

### The farm with the farmer next to his home and a cropfield.

![docs/screenshots/farm.png](screenshots/farm.png)

### An example of a popup menu

![docs/screenshots/popup_menu.png](screenshots/popup_menu.png)

### A wheat crop in one of its earlier growth stages

![docs/screenshots/crop_growing.png](screenshots/crop_growing.png)


## PLANNED FEATURES

- **More crops** -
- **Resting** -
- **Saving game** -
- **Weather effects** -
- **Inventory and warehouse** -
- **Market** -
- **Animals** -
- **Building** -
- **Tools** -
- **Upgrades** -

## DESIGN

> ### Problem1
>> #### Problem in Context
>> pic1
>
>> #### The Pattern
>> tp1
>
>> #### Implementation
>> i1
>
>> #### Consequences
>> c1
>
> ### Problem2 ...


## KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> ### CodeSmell1
>> descrição1
>
>> refactoringSugestion1
>
> ### CodeSmell2 ...


## TESTING

### COVERAGE REPORT
![Coverage Report](screenshots/coverage.png)

### [MUTATION TESTING REPORT](../build/reports/pitest)


## SELF-EVALUATION

- Diogo Costa: %
- Pedro Gonçalo Correia: %
- Rui Alves: %
