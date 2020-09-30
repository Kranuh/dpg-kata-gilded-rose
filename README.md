# DPG - Technical Assignment
## Acknowledgments

After reading the introduction text for the Kata I tried to get the TextTest setup configured. Unfortunately I faced quite some problems during this process. My python versions we’re not configured properly, causing PIP to install TextTest on my Mac Python 2.7 version. Not being experienced in python, this caused some big delays in the beginning. I eventually got TextTest configured and working, but ran into another obstacle; it wasn’t able to find the correct TextTestFixture. I tried changing the classpath in the environment but did not succeed. After all this I decided to complete the exercise using the junit framework. 

## Commit history
I tried explaining what I did per commit, here is a little overview.

043e63eff25 - Struggling with the TextTest setup

2fa9e5540e3 - Added unit tests as per the requirements. Also added a test for the future improvement of the ‘Conjured’ item. This tests obviously failed, but that was intended.

bb29ee99d8f - Started adding some comments to the large `updateQuality` function. Just to clarify where certain logic was located.

c3f51c4f126 - This commit contains the largest chunk of refactoring. My process here began with creating an abstraction for the different types of items. Eventually adding the `QualityChanger` interface in order to move the logic into their own classes but also to differentiate between ‘regular’ items and the ‘Legendary’ items.

16bbe2403ec - This is the totally cleaned up version, and the final commit that involves the exercise directly.

9b967025cc8 - This commit contains some general cleanup, the editing of this readme.md but also a git patch. It’s located in the root folder and it’s called _android\_style.patch_. This is a super quick attempt at applying some CLEAN principles to the GildedRose project. I didn’t spend a lot of time on this however since I felt that wasn’t the point of the exercise. This is also the reason I included it as a patch.

The final commit again involves some general cleanup and small things I changed before sending the assignment out.

